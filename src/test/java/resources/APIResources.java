package resources;

public enum APIResources {

	LocationSearchAPI("/api/location/search/"),
	LocationAPI("/api/location/");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
