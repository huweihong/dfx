package top.dteam.dfx;

public class Launcher extends io.vertx.core.Launcher {
    public static void main(String[] args) {
        //Force to use slf4j
        System.setProperty("vertx.logger-delegate-factory-class-name",
                "io.vertx.core.logging.SLF4JLogDelegateFactory");

        new Launcher().dispatch(args);
    }
}
