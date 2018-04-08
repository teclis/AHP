package es.org.israel.main;

public class SystemChecks {
	public SystemChecks() {
	}
	
	public boolean isJavaVersionSupported() {
		String[] ver = System.getProperty("java.version").split("\\.");
		if (Integer.parseInt(ver[1]) >= 7) {
			return true;
		} else {
			return false;
		}
	}
}
