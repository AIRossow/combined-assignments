package com.cooksys.ftd.assignments.file;

import com.cooksys.ftd.assignments.file.model.Contact;
import com.cooksys.ftd.assignments.file.model.Instructor;
import com.cooksys.ftd.assignments.file.model.Session;
import com.cooksys.ftd.assignments.file.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Creates a {@link Student} object using the given studentContactFile.
     * The studentContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param studentContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return a {@link Student} object built using the {@link Contact} data in the given file
     */
	public static Student readStudent(File studentContactFile, JAXBContext jaxb) {
    	Student estudiante = new Student();
    	try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Contact student = (Contact) jaxbUnmarshaller.unmarshal(studentContactFile);
			estudiante.setContact(student);
			return estudiante;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    /**
     * Creates a list of {@link Student} objects using the given directory of student contact files.
     *
     * @param studentDirectory the directory of student contact files to use
     * @param jaxb the JAXB context to use
     * @return a list of {@link Student} objects built using the contact files in the given directory
     */
    public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
    	List<Student> list = new ArrayList<Student>(); 
    	File dir[] = studentDirectory.listFiles();
    	if(dir != null) {
    		for(File studentContactFile : dir) {
    			list.add(readStudent(studentContactFile,jaxb));
    		}
    	}
    	return list; // TODO
    }

    /**
     * Creates an {@link Instructor} object using the given instructorContactFile.
     * The instructorContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param instructorContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return an {@link Instructor} object built using the {@link Contact} data in the given file
     */
    public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) {
    	Instructor instruct = new Instructor();
    	try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Contact instrCont = (Contact) jaxbUnmarshaller.unmarshal(instructorContactFile);
			instruct.setContact(instrCont);
			return instruct;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    /**
     * Creates a {@link Session} object using the given rootDirectory. A {@link Session}
     * root directory is named after the location of the {@link Session}, and contains a directory named
     * after the start date of the {@link Session}. The start date directory in turn contains a directory named
     * `students`, which contains contact files for the students in the session. The start date directory
     * also contains an instructor contact file named `instructor.xml`.
     *
     * @param rootDirectory the root directory of the session data, named after the session location
     * @param jaxb the JAXB context to use
     * @return a {@link Session} object built from the data in the given directory
     */
    public static Session readSession(File rootDirectory, JAXBContext jaxb) {
        Session sesh = new Session();
        File dir[] = rootDirectory.listFiles();
        File dir2[] = dir[0].listFiles();
        File dir3[] = dir2[0].listFiles();
        sesh.setLocation(dir[0].getName().toString());
        sesh.setStartDate(dir2[0].getName().toString());
        sesh.setStudents(readStudents(dir3[1],jaxb));
        sesh.setInstructor(readInstructor(dir3[0], jaxb));
        //rootDirectory.
//    	if(dir != null) {
//    		for(File everything : dir) {
//    			sesh.setLocation(everything.getName().toString());
//    		}
//    	}
    	return sesh;
    }

    /**
     * Writes a given session to a given XML file
     *
     * @param session the session to write to the given file
     * @param sessionFile the file to which the session is to be written
     * @param jaxb the JAXB context to use
     */
    public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {
    	try {
			Marshaller jaxbMarshaller = jaxb.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(session, sessionFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Main Method Execution Steps:
     * 1. Configure JAXB for the classes in the com.cooksys.serialization.assignment.model package
     * 2. Read a session object from the <project-root>/input/memphis/ directory using the methods defined above
     * 3. Write the session object to the <project-root>/output/session.xml file.
     *
     * JAXB Annotations and Configuration:
     * You will have to add JAXB annotations to the classes in the com.cooksys.serialization.assignment.model package
     *
     * Check the XML files in the <project-root>/input/ directory to determine how to configure the {@link Contact}
     *  JAXB annotations
     *
     * The {@link Session} object should marshal to look like the following:
     *      <session location="..." start-date="...">
     *           <instructor>
     *               <contact>...</contact>
     *           </instructor>
     *           <students>
     *               ...
     *               <student>
     *                   <contact>...</contact>
     *               </student>
     *               ...
     *           </students>
     *      </session>
     */
    public static void main(String[] args) {
    	Session session = new Session();
    	File Sesh = new File("C:\\code\\java\\combined-assignments\\4-file-io-serialization\\input");
    	File Output = new File("C:\\code\\java\\combined-assignments\\4-file-io-serialization\\output\\session.xml");
    	try{
    		JAXBContext jaxb = JAXBContext.newInstance(Session.class);
    		JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
    		session = readSession(Sesh, jaxbContext);
    		writeSession(session, Output, jaxb);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
