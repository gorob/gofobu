package photobook;

public interface IMarkFeature {
    void markRegion();

    void demarkRegionIfNecessary();

    void demarkOtherRegionsOfScene();
}
