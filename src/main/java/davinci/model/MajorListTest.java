import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class MajorListTest {
    private MajorList majors;
    private MajorList majorsUUID;

    @Before
    public void Setup() {
        majors = new MajorList();
    }

    @Before
    public void SetupUUID() {
        majorsUUID = new MajorList();
    }

    @Test
    public void addMajortoMajorListTest() {
        Major major = DataLoader.getMajors().get(1);
        majors.addMajortoMajorList(major);

        Major retrievedMajor = majors.getByUUID(String.valueOf(major.getID()));

        assertNotNull(retrievedMajor);
        assertEquals(major, retrievedMajor);
    }
    
    @Test
    public void getByUUIDTest() {
        Major major1 = DataLoader.getMajors().get(0);
        Major major2 = DataLoader.getMajors().get(1);

        majorsUUID.addMajortoMajorList(major1);
        majorsUUID.addMajortoMajorList(major2);

        Major retrievedMajor1 = majorsUUID.getByUUID(String.valueOf(major1.getID()));
        Major retrievedMajor2 = majorsUUID.getByUUID(String.valueOf(major2.getID()));

        //Create non-existent major
        Major retrievedMajor3 = majorsUUID.getByUUID("3058a273-5530-43ab-8ffb-592f0e3dc3b1");

        assertNotNull(retrievedMajor1);
        assertNotNull(retrievedMajor2);

        assertEquals(major1, retrievedMajor1);
        assertEquals(major2, retrievedMajor2);

        //Make sure fake major returns null.
        assertNull(retrievedMajor3);

    }
}
