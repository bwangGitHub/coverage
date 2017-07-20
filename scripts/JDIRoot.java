/* Build with

/usr/lib/jvm/java-6-sun/bin/java -cp .:/usr/lib/jvm/java-6-sun/lib/tools.jar \
JDIRoot Hello

Run with

/usr/lib/jvm/java-6-sun/bin/java -cp .:/usr/lib/jvm/java-6-sun/lib/tools.jar \
JDIRoot Hello

*/


import com.sun.jdi.VirtualMachine;
import com.sun.jdi.Bootstrap;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.request.*;
import com.sun.jdi.event.*;

import java.util.Map;
import java.util.List;

import java.io.PrintWriter;

class JDIRoot
{
    // Running remote VM
    private final VirtualMachine vm;

	// Class patterns for which we don't want events
    private String[] excludes = {"java.*", "javax.*", "sun.*",
                                 "com.sun.*"};


	private class EventThread extends Thread
	{
		private final VirtualMachine vm;
		private final PrintWriter writer;

		EventThread(VirtualMachine vm, PrintWriter writer)
		{
			super("event-handler");
			this.vm = vm;
			this.writer = writer;
		}

		/**
		 * Run the event handling thread.
		 * As long as we are connected, get event sets off
		 * the queue and dispatch the events within them.
		 */
		public void run() {
			EventQueue queue = vm.eventQueue();
			while (true) {
				try {
					EventSet eventSet = queue.remove();
					EventIterator it = eventSet.eventIterator();
					while (it.hasNext()) {
						Event event = it.nextEvent();
						if (event instanceof MethodEntryEvent) {
							handleMethodEntry((MethodEntryEvent)event);
						}						
					}
					eventSet.resume();
				}
				catch (InterruptedException exc) {
					// Ignore
				}
				catch (VMDisconnectedException discExc) {
					break;
				}
			}
		}

		private void handleMethodEntry(MethodEntryEvent event)
		{
			Method method = event.method();
			writer.println(method);

			try {
				List <Location> locations = method.allLineLocations();
				writer.println(locations);
			}
			catch (AbsentInformationException abs) {
				writer.println("No debugging information");
			}
		}
	}

	JDIRoot(String[] args) {
		LaunchingConnector connector =
			Bootstrap.virtualMachineManager().defaultConnector();

		StringBuffer sb = new StringBuffer();
		for (String arg:args) {
			sb.append(arg);
		}

		System.out.println("VM args: " + sb.toString());
		Map <String, Connector.Argument> arguments = connector.defaultArguments();
		arguments.get("main").setValue(sb.toString());

		try {
            vm = connector.launch(arguments);
        }
		catch (Exception exc) {
			throw new Error("Unable to launch target VM: " + exc);
        }

		EventRequestManager mgr = vm.eventRequestManager();
		MethodEntryRequest menr = mgr.createMethodEntryRequest();
		for (String exclude : excludes) {
            menr.addClassExclusionFilter(exclude);
        }
		menr.setSuspendPolicy(EventRequest.SUSPEND_NONE);
		menr.enable();

        ThreadDeathRequest tdr = mgr.createThreadDeathRequest();
        // Make sure we sync on thread death
        tdr.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        tdr.enable();

        PrintWriter writer = new PrintWriter(System.out);
		EventThread eventThread = new EventThread(vm, writer);
		eventThread.start();
		vm.resume();
		try {
            eventThread.join();
		}
		catch (InterruptedException exc) {
            // we don't interrupt
		}
		writer.close();
	}

	public static void main(String args[])
	{
		System.out.println("JDI Root started");
		new JDIRoot(args);
	}
}
