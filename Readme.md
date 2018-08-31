Firewall Prototype -

Idea is to "allow" or "block" the packet, based on the "Allowed Rules" in the CSV File.

This folder contains 2 versions of the Firewall Prototype. Please find the details about both
these versions below -


First Version - (Firewall_v1)

- FirewallClient is the entry point to the application.
- Every Class has the prolog, which briefly talks about what this particular class does.

Second Version - (Firewall_v2)

- I have tried to add some more enhancements to the code in version 1.
- Divided the allowedRules list into the small chunks of lists.
  Created a thread pool with certain fixed number of threads.
  Each thread in the thread pool is responsible to check if the incoming packet
  matches any given rule (in it's respective chunk).

- Note - I have used Guava's Lists.partition(List, int) method to this.
https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/collect/Lists.html#partition-java.util.List-int-

 For this you need to import a jar in your editor, and then you can include the
 necessary imports.

 Steps I followed to import Jar in my Eclipse Project on Mac are as follows -
 1. Right click on the project -> Build Path -> Configure Build Path.
 2. Under Libraries tab, click Add Jars, and select the jar file.
    The code folder (Firewall_v2) has the jar file in it.

I have tried running the code on large number of entries (around 500K), and code runs just fine.
