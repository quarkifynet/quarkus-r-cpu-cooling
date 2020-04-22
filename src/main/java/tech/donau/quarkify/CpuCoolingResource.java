package tech.donau.quarkify;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

@Path("/cooling")
public class CpuCoolingResource {
    public static Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws IOException {
        final URL cpuCoolingR = getClass().getClassLoader().getResource("cpu_cooling.R");
        System.out.println(cpuCoolingR);
        Value needs_cooling = context.eval(Source.newBuilder("R", cpuCoolingR).build());
        return needs_cooling.asDouble() > 0.8 ? "Enable cooling" : "No need to enable cooling";
    }
}