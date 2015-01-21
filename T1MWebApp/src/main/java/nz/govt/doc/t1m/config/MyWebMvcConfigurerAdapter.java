package nz.govt.doc.t1m.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.HashSet;

/**
 */
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MyWebMvcConfigurerAdapter.class);

    @Autowired
    protected Environment environment;

    private String version;

    public MyWebMvcConfigurerAdapter(String version) {
        this.version = version;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        boolean isDEVEnvironment = new HashSet<>(Arrays.asList(environment.getActiveProfiles())).contains("DEV");

        // HACK for now
        isDEVEnvironment = true;

        Integer cachePeriod;
        if(isDEVEnvironment) {
            cachePeriod = 0;
            log.info("Detected DEV environment - setting cache period to " + cachePeriod);
        }
        else {
            cachePeriod = 31 * 24 * 60 * 60 * 1000;
            log.info("Detected a non DEV environment - setting cache period to " + cachePeriod);
        }

        String[] resourceRoots = new String[]{
                //"bower_components",
                "css",
                //"img",
                //"js",
                //"partials",
                //"app",
        };

        for(String nextResourceRoot : resourceRoots) {
            String pathPattern = "/resources/" + version + "/" + nextResourceRoot + "/**";
            String resourceLocation = "classpath:/static/" + nextResourceRoot + "/**";
            registry.addResourceHandler(pathPattern).addResourceLocations(resourceLocation).setCachePeriod(cachePeriod);
            log.info("Adding resource mapping: " + pathPattern + " -> " + resourceLocation);
        }
    }
}
