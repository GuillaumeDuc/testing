import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.*;

class MyPointTest {

	private MyPoint MyP;
	
	@BeforeEach
	public void setUp(){
		MyP = new MyPoint();
	}
	
	@AfterEach
	public void tearDown() {
		MyP = null;
	}
	
	@Test
	void testMyPoint() {
		assertEquals(MyP.getX(),0d);
		assertEquals(MyP.getY(),0d);
	}

	@Test
	void testMyPointDoubleDouble() {
		MyP = new MyPoint(2,1);
		assertEquals(MyP.getX(),2);
		assertEquals(MyP.getY(),1);
	}

	@Test
	void testMyPointMyPoint() {
		MyP = new MyPoint(2,1);
		MyPoint MyPTested = new MyPoint(MyP);
		assertEquals(MyPTested.getX(), MyP.getX());
		assertEquals(MyPTested.getY(),MyP.getY());
	}
	
	@Test
	void testMyPointNull() {
		MyP = null;
		MyPoint MyP3 = new MyPoint(MyP);
		assertEquals(MyP3.getX(),0);
		assertEquals(MyP3.getY(),0);
	}

	@Test 
	void testSetX() {
		MyP.setX(1);
		assertEquals(MyP.getX(),1);
	}

	@Test
	void testSetY() {
		MyP.setY(1);
		assertEquals(MyP.getY(),1);
	}

	@Test
	void testGetX() {
		MyP = new MyPoint(2,3);
		assertEquals(MyP.getX(),2);
	}

	@Test
	void testGetY() {
		MyP = new MyPoint(2,3);
		assertEquals(MyP.getY(),3);
	}
	
	@Test
	void testSetXNan() {
		MyP = new MyPoint(2,3);
		MyP.setX(Double.NaN);
		assertEquals(MyP.getX(),2.0);
	}
	
	@Test
	void testSetYNan() {
		MyP = new MyPoint(2,3);
		MyP.setY(Double.NaN);
		assertEquals(MyP.getY(),3.0);
	}

	@Test
	void testScale() {
		MyP = new MyPoint(5, 8);
		MyPoint MyP3 = new MyPoint(MyP);
		MyP3 = MyP3.scale(2);
		assertEquals(MyP3.getX(),10);
		assertEquals(MyP3.getY(),16);
	}
	
	@Test
	void testHorizontalSymmetry() {
		MyP = new MyPoint(4, 1);
		MyPoint MyP3 = new MyPoint(3, 8);
		MyP = MyP.horizontalSymmetry(MyP3);
		assertEquals(MyP.getX(),2);
		assertEquals(MyP.getY(),1);
	}
	
	@Test
	void testHorizontalSymmetryShouldThrowIllegalArgumentException() {
	    assertThrows(IllegalArgumentException.class,
	            ()->{
	            	MyP = new MyPoint(4, 1);
	            	MyPoint MyP3 = null;
	            	MyP = MyP.horizontalSymmetry(MyP3);
	            });
	}
	
	@Test
	void testComputeAngleNull() {
		MyP = null;
		MyPoint MyP2 = new MyPoint(2,1);
		double angle = MyP2.computeAngle(MyP);
		assertEquals(angle,Double.NaN);
	}
	
	@Test
	void testRotatePoint() {
		MyP = null;
		MyPoint MyP2 = new MyPoint(2,1);
		MyP2 = MyP2.rotatePoint(MyP, 5);
		assertEquals(MyP2,null);
	}
	
	@Test
	void testCentralSymmetry() {
	assertThrows(IllegalArgumentException.class,
			()->{
				MyP = new MyPoint(4, 1);
	            	MyPoint MyP2 = null;
	            	MyP = MyP.centralSymmetry(MyP2);
	            });
	}
	
	@Test
	void testTranslate() {
		MyP.translate(2,3);
		assertEquals(MyP.getX(),2);
		assertEquals(MyP.getY(),3);
	}
	
	@Test
	void testTranslateNaN() {
		MyP.translate(Double.NaN,Double.NaN);
		assertEquals(MyP.getX(),0);
		assertEquals(MyP.getY(),0);
	}
	
	@Test
	void testTranslateNull() {
		MyP.translate(null);
		assertEquals(MyP.getX(),0);
		assertEquals(MyP.getY(),0);
	}
	@Test
	void setPointTest() {

		Random mockX = EasyMock.createMock(Random.class);
		Random mockY = EasyMock.createMock(Random.class); 

		EasyMock.expect( mockX.nextInt() ).andReturn( 1 );
		EasyMock.expect(mockY.nextInt()).andReturn(2);
		
		EasyMock.replay( mockX, mockY );
		
		MyP.setPoint(mockX,mockY);
		
		assertEquals(MyP.getX(), 1.0);
		assertEquals(MyP.getY(), 2.0);
		}
	
	@Test
	void translateITranslationTest() {
		ITranslation translation = EasyMock.createMock(ITranslation.class);
		EasyMock.expect( translation.getTx() ).andReturn( 1 );
		EasyMock.expect( translation.getTy() ).andReturn( 2 );
		EasyMock.replay( translation );
		MyP.translate(translation);
		assertEquals(MyP.getX(),1.0);
		assertEquals(MyP.getY(),2.0);
	}

}
