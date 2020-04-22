package tech.donau.quarkify;

import org.graalvm.polyglot.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

@Path("/cooling")
public class CpuCoolingResource {
    public static Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("grads") Double grads) throws IOException {
        final URL cpuCoolingR = getClass().getClassLoader().getResource("cpu_cooling.R");
        context.eval(Source.newBuilder("R", cpuCoolingR).build());
        final Value needs_cooling = context.getBindings("R").getMember("needs_cooling");
        final Value result = needs_cooling.execute(grads);
        return result.asDouble() > 0.8 ? "Enable cooling" : "No need to enable cooling";
    }
}