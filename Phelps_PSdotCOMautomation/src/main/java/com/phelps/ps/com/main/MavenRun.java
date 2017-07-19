package com.phelps.ps.com.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class MavenRun {

	public static void main(String[] args) throws IOException {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("pom.xml"));
		if (args.length > 0) {
			if (args[0] != null && args[1] != null) {
				Properties projectProperties = new Properties();
				projectProperties.setProperty("mode", args[0]);
				projectProperties.setProperty("baseURL", args[1]);
				projectProperties.setProperty("system", args[2]);
				request.setProperties(projectProperties);
			}
		}

		ArrayList<String> goals = new ArrayList<String>();
		goals.add("clean");
		goals.add("test");
		if (args.length == 8) {
			goals.add("-Dgroups=" + args[7]);
		}

		request.setGoals(goals);
		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
		try {
			invoker.execute(request);
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	}
}
