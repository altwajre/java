package com.packt.legacy.powermockito;

public class SoftwareInstaller {
	private final SystemVerifier systemVerifier;

	public SoftwareInstaller(SystemVerifier systemVerifier) {
		this.systemVerifier = systemVerifier;
	}

	public boolean install(String packageName) {
		if (systemVerifier.isInstallable()) {
			// install something
			return true;
		}

		return false;
	}

}
