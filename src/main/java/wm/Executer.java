package wm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.maven.cli.MavenCli;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;


public class Executer {

	public static void main(String[] args) {
		try {
			MavenCliMethod(MvnType.PACKAGE,"C:\\Users\\simona.parodi\\Documents\\Cond\\WK\\wkSims21\\DipartimentoWEBSimsTrunk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void MavenCliMethod(String mvnType, String urlProject) {
		MavenCli cli = new MavenCli();
		cli.doMain(new String[]{"clean", mvnType}, urlProject, System.out, System.out);
	}

	
	public static void MavenInvocationMethod() throws MavenInvocationException {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile( new File( "/path/to/pom.xml" ) );
		request.setGoals( Arrays.asList( "clean", "install" ) );

		Invoker invoker = new DefaultInvoker();
		invoker.execute( request );
	}
	
	public static void SimpleMethod() throws IOException {
		Runtime.getRuntime().exec("mvn clean install");
	}
	
}
