import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class DBHandlerTestCase {

	private String name, transcription, description;
	private Term testingTerm;
	
	@Before
	public void setUp() throws Exception {
		
		this.name = "termName";
		this.transcription = "transcriptionN";
		this.description = "descriptionN";
		
		testingTerm = new Term(name, transcription, description);
		
	}

	@Test
	public void testGetTermList() throws SQLException {
		
		
		ResultSet rs = DBHandler.getTermList();
		
		boolean hasRow = rs.next();
		
		Assert.assertTrue(hasRow);
		
	}

	@Test
	public void testAddNewTerm() {
		
		DBHandler.addNewTerm(testingTerm);
		
		boolean isAdded = DBHandler.isExist(name);
		DBHandler.deleteTerm(testingTerm.getName());
		
		Assert.assertTrue(isAdded);
		
	}

	@Test
	public void testDeleteTerm() {
		
		DBHandler.addNewTerm(testingTerm);
		DBHandler.deleteTerm(testingTerm.getName());
		
		boolean isDeleted = !(DBHandler.isExist(testingTerm.getName()));
		
		Assert.assertTrue(isDeleted);
	}

	@Test
	public void testUpdateTerm() {
		
		DBHandler.addNewTerm(testingTerm);
		testingTerm.setName("newTermName");
		DBHandler.updateTerm(testingTerm, name);
		
		boolean isEdited = DBHandler.isExist(testingTerm.getName());
		DBHandler.deleteTerm(testingTerm.getName());
		
		Assert.assertTrue(isEdited);
		
	}

}
