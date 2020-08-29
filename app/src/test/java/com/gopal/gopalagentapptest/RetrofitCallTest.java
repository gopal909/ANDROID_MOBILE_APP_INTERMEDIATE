package com.gopal.gopalagentapptest;

import com.gopal.gopalagentapptest.API.ClientApi;
import com.gopal.gopalagentapptest.model.Result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
public class RetrofitCallTest {
    private MainActivity mainActivity;

    @Mock
    private ClientApi mockRetrofitApiImpl;

    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mainActivity = controller.get();

        // Then we need to swap the retrofit api impl. with a mock one
        // I usually store my Retrofit api impl as a static singleton in class RestClient, hence:

        //RestClient.setApi(mockRetrofitApiImpl);

        controller.create();
    }

    @Test
    public void shouldFillAdapter() throws Exception {

    }


}
