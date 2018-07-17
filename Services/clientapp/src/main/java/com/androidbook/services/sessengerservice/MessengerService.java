package com.androidbook.services.sessengerservice;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class MessengerService {
    public static final int MSG_REGISTER_CLIENT = 1;

    /**
     * Command to the service to unregister a client, ot stop receiving callbacks
     * from the service.  The Message's replyTo field must be a Messenger of
     * the client as previously given with MSG_REGISTER_CLIENT.
     */
    public static final int MSG_UNREGISTER_CLIENT = 2;

    /**
     * Command to service to set a new simple value.  This can be sent to the
     * service to supply a new value.
     */
    public static final int MSG_SET_SIMPLE_VALUE = 3;

    /**
     * Command to service to set several values, not just one or two.
     */
    public static final int MSG_SET_COMPLEX_VALUE = 4;

    public static final String TAG = "MessengerService";
}
