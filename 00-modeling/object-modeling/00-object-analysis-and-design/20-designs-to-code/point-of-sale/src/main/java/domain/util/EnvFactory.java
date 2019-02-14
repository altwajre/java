package domain.util;

public enum EnvFactory {
  INSTANCE;

  public String getEnv() {
    String env = "dev";

    if(System.getenv().containsKey("ENV")) {
      env = System.getenv().get("ENV").trim().toLowerCase();
    }

    return env;
  }
}
