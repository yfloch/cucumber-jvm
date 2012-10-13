package cucumber.examples.squeaker;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getProperty("port", "8080"));
        Server server = new Server(port);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        String war = Main.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        System.out.println("war = " + war);
        webapp.setWar(war);
        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
