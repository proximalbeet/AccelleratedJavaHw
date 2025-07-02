import java.util.Calendar;

/**
 * Week3Program: Solves problems in RadioCarbon Dating
 *
 * @author Nathan Kenney - CS1131
 */
public class Week3Program {

    // Constants that cannot be changed
    public final int CARBON_HALF_LIFE = 5730;
    public final double RATE_OF_DECAY = -0.693f;

    // Made the edge case a method that way I can nest it 
    // in any of the caculations to prevent repetition
    double exceptionIfOutOfBounds ( double percent ) {
	    if ( percent >= 0.0 && percent <= 1.0 ) {
	        return percent;  
 	    } else {
	        throw new IllegalArgumentException(
	            "Must be a percentage between 0 and 1" );
	    }
    }

    /**
     * Given the age of an artifact in years, 
     * return the percent of C14 left in it. Here a percentage is a
     * double in the range [0.0, 1.0]. 
     * Remember that N / N0 is the percent of C14 remaining in the object.
     * @param age - the age of an artifact in years
     * @return the percent of C14 remaining in the artifact
     * @throws an exception if percentOfCarbonRemaining 
     * is not between 0.0 and 1.0
     */
    double radiocarbonPercent ( int age ) {
        // Fill in the required expressions
        // Did not round nor truncate percentOfCarbonRemaining 
	    // because it was not in the directions
        double percentOfCarbonRemaining = Math.exp( ( age * RATE_OF_DECAY ) / 
	        CARBON_HALF_LIFE );
	    // Had to make edge case a double to return percentOfCarbonRemaining
	    double result = exceptionIfOutOfBounds( percentOfCarbonRemaining );
        return result;
    }

    /**
     * Given the percentage of C14 remaining in an artifact, 
     * return the age of the artifact in years.
     * Here a percentage is a double in the range [0.0, 1.0].
     * Your result should be truncated, not rounded.
     * @param p - the percentage of C14 remaining in an artifact
     * @return the age of the artifact in years
     * @throws an exception if p is not between 0.0 and 1.0
     */
    int radiocarbonAge ( double p ) {
        // Fill in the required expressions
        // Edge case
	    exceptionIfOutOfBounds( p );
	    double ageOfArtifact = ( ( Math.log( p ) / RATE_OF_DECAY ) * 
            CARBON_HALF_LIFE );
        return ( int ) Math.floor( ageOfArtifact );
    }

    /**
     * Given the percentage of C14 remaining in an artifact, return the year 
     * of the artifact was created or the
     * organism died. The date is the current year minus the 
     * age of the artifact.
     * Your result should be truncated, not rounded.
     * @param p the percentage of C14
     * @return The date is the current year minus the age of the artifact.
     * @throws an exception if p is not between 0.0 and 1.0
     */
    int radiocarbonDate ( double p ) {
        // Fill in the required expressions
	    // Edge case
	    exceptionIfOutOfBounds( p );
        int currentYear = Calendar.getInstance().get( Calendar.YEAR );
        int artifactYear = ( currentYear - radiocarbonAge( p ) );
        return artifactYear;
    }

    // Test code
    public static void main ( String [ ] args ) {
        Week3Program self = new Week3Program( );
        System.out.println( "Test radiocarbonPercent( age )." );
        int age = 1845;
        double percentage = self.radiocarbonPercent( age );
        System.out.printf( "radiocarbonPercent( %d ) = %f%%. ", age, 
            percentage );
        if ( ( int ) ( percentage * 100 ) == 80 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonAge( percentage )." );
        percentage = 0.80;
        age = self.radiocarbonAge( percentage );
        System.out.printf( "radiocarbonAge( %f ) = %d years old. ", 
            percentage, age );
        if ( Math.abs(age - 1845 ) <= 1.0 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonDate( percentage )." );
	    percentage = 0.80;
	    int year = self.radiocarbonDate( percentage );
	    age = 1845;
		System.out.printf( "radiocarbonDate( %f ) = the year %d. ", 
            percentage, year );
	    if ( Math.abs( year - ( Calendar.getInstance().get( Calendar.YEAR ) - 
                age ) ) <= 5.0 ) {
		    System.out.println( "Success!\n" );
	    } else {
		    System.out.println( "FAILED!\n" );
	    }
    }
}
