package top.dteam.dfx.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DfxConfig {

    private static final Logger logger = LoggerFactory.getLogger(DfxConfig.class);

    int port
    String host = '0.0.0.0'
    Map<String, String> mappings

    static DfxConfig load(String file = System.getProperty('conf')) {
        if (!file) {
            throw new FileNotFoundException("Please set config file first!")
        }

        File f = new File(file)
        if (f.isFile()) {
            build(f.text)
        } else {
            throw new FileNotFoundException("No such file or directory: ${file}")
        }
    }

    static DfxConfig build(String config) {
        ConfigSlurper slurper = new ConfigSlurper()
        ConfigObject configObject = slurper.parse(config)

        DfxConfig dfxConfig = new DfxConfig()
        dfxConfig.port = configObject.port
        dfxConfig.mappings = [:]
        (configObject.keySet() - ['port']).each {
            dfxConfig.mappings[it] = configObject[it].plugin
        }

        logger.debug("mappings: {}", dfxConfig.mappings)

        dfxConfig
    }

}