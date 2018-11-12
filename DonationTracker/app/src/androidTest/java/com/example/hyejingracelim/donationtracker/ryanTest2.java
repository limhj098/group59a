import android.content.Context;
import android.view.View;

import com.example.hyejingracelim.donationtracker.LocationsActivity;

import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;

//import static com.google.common.truth.Truth.assertThat;

public class UnitTestSampleJava {
    private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        LocationsActivity myObjectUnderTest = new LocationsActivity();

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.readData(context);

        // ...then the result should be the expected one.
        assertThat(result).isEqualTo(FAKE_STRING);
    }
}