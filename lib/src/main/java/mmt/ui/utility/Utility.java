package mmt.ui.utility;

import com.aventstack.extentreports.Status;

import mmt.ui.frameworksetup.ThreadPackage;

public class Utility {

	public void info(String s) {
		ThreadPackage.getInstance().getExtent().info(s);
	}
	
	public void testpass(String s) {
		ThreadPackage.getInstance().getExtent().log(Status.PASS, s);
	}
}
