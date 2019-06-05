package org.nasdanika.html.tests;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.junit.internal.TextListener;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class TestRunner implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		
        return runJUnit();
	}

	private Object runJUnit() {
		JUnitCore jUnitCore = new JUnitCore();
        RunListener runListener = new TextListener(System.out);
        jUnitCore.addListener(runListener);

        Result result = jUnitCore.run(Computer.serial(), HtmlTests.class);
        
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        //System.out.println(result);
        
        return result.getFailureCount()==0 ? 0 : 1;
	}
	
	@Override
	public void stop() {
		// NOP
	}

}
