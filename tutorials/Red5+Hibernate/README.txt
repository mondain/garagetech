Red5 + Hibernate Revisited by Carl Sziebert (http://sziebert.net/posts/hibernate-red5-revisited)

-----

This sample application utilizes Spring 2.5.x and Hibernate 3.3.1. It provides 
full transactional data access to look up user credentials during the initial 
connection attempt to a Red5 media server.

-----

What's new in this version

1. Migration to new version of Spring/Hibernate/Red5. (Note, the red5.jar is pulled directly from the Red5 CI server. Though, you should consider using the JAR found in your install.)
2. No longer depends on HibernateTemplate in Spring.  The application now directly accesses the Hibernate session.
3. Moved to Ivy for dependency management.
4. Migrated to AS3 and simplified the flash client code.
5. Migrated to a new namespace/package scheme.
6. Migrated to the new Red5 logging mechanism. Log files for the application are now found in the Red5 logs directory.
7. Added in simple password hashing to provide a more secure example. Uses the Adobe AS3 corelib project found here: http://code.google.com/p/as3corelib/

-----

A couple of notes about this example application...

1. This example is provided with absolutely NO (none, nada, zip, zilch, zero) warranty or support. Use it at your own risk.
2. For the project to work in your IDE, you'll need to insure that all the required dependencies have been copied into your project.  That means you need to have run the ant retrieve target, though it should have been run for you when you compiled the example.  BTW, project files for Intellij are included free of charge.
3. Requires the MySQL database, running on localhost with the schema 'red5' created and the user 'red5' (and password 'L3tm3!n') having access to said schema. * See jdbc.properties.
4. Hibernate will create the necessary tables needed to run the application at startup. However, you will need to add any and all 'User' entries into the users table.  Otherwise, all connection attempts will be rejected.
5. If you wish to run against a different database or use alternate db credentials, you had better know what you are doing with Spring, Hibernate and your database.  ;-)

-----

Getting Started

To use the application as provided, all you will need to do is drop the 'WEB-INF' 
directory into a Red5 application directory named 'hibernate'.  Make sure that you've got MySQL running
and configured correctly with the info provided.  After that you can test your install with the
simple client I've provided (swf/test.html).