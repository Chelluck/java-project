package resourceServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.ResourceService;
import resources.TestResource;

public class ResourceServerI implements ResourceServer {
    private TestResource resource;
    static final Logger logger = LogManager.getLogger(ResourceServerI.class.getName());

    public ResourceServerI() {
    }

    @Override
    public String getName() {
        return resource.getName();
    }

    @Override
    public int getAge() {
        return resource.getAge();
    }

    public String getNameClass() {
        return resource.getClass().toString();
    }

    @Override
    public void readResource(String path) {
        resource = ResourceService.instance().getResource(path, TestResource.class);
        logger.info("Resource loaded");
    }
}
