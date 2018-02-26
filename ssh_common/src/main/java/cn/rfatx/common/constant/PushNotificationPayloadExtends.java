package cn.rfatx.common.constant;

import java.util.List;

import javapns.notification.Payload;
import javapns.notification.exceptions.PayloadAlertAlreadyExistsException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONNull;
import org.json.JSONObject;

public class PushNotificationPayloadExtends
        extends Payload
{
    private static final int MAXIMUM_PAYLOAD_LENGTH = 256;
    private JSONObject apsDictionary;

    public static PushNotificationPayloadExtends alert(String message)
    {
        if (message == null) {
            throw new IllegalArgumentException("Alert cannot be null");
        }
        PushNotificationPayloadExtends payload = complex();
        try
        {
            payload.addAlert(message);
        }
        catch (JSONException e) {}
        return payload;
    }

    public static PushNotificationPayloadExtends badge(int badge)
    {
        PushNotificationPayloadExtends payload = complex();
        try
        {
            payload.addBadge(badge);
        }
        catch (JSONException e) {}
        return payload;
    }

    public static PushNotificationPayloadExtends sound(String sound)
    {
        if (sound == null) {
            throw new IllegalArgumentException("Sound name cannot be null");
        }
        PushNotificationPayloadExtends payload = complex();
        try
        {
            payload.addSound(sound);
        }
        catch (JSONException e) {}
        return payload;
    }

    public static PushNotificationPayloadExtends combined(String message, int badge, String sound)
    {
        if ((message == null) && (badge < 0) && (sound == null)) {
            throw new IllegalArgumentException("Must provide at least one non-null argument");
        }
        PushNotificationPayloadExtends payload = complex();
        try
        {
            if (message != null) {
                payload.addAlert(message);
            }
            if (badge >= 0) {
                payload.addBadge(badge);
            }
            if (sound != null) {
                payload.addSound(sound);
            }
        }
        catch (JSONException e) {}
        return payload;
    }

    public static PushNotificationPayloadExtends test()
    {
        PushNotificationPayloadExtends payload = complex();
        payload.setPreSendConfiguration(1);
        return payload;
    }

    public static PushNotificationPayloadExtends complex()
    {
        PushNotificationPayloadExtends payload = new PushNotificationPayloadExtends();
        return payload;
    }

    public static PushNotificationPayloadExtends fromJSON(String rawJSON)
            throws JSONException
    {
        PushNotificationPayloadExtends payload = new PushNotificationPayloadExtends(rawJSON);
        return payload;
    }

    public PushNotificationPayloadExtends()
    {
        this.apsDictionary = new JSONObject();
        try
        {
            JSONObject payload = getPayload();
            if (!payload.has("aps")) {
                payload.put("aps", this.apsDictionary);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public PushNotificationPayloadExtends(String rawJSON)
            throws JSONException
    {
        super(rawJSON);
        try
        {
            JSONObject payload = getPayload();
            this.apsDictionary = payload.getJSONObject("aps");
            if (this.apsDictionary == null)
            {
                this.apsDictionary = new JSONObject();
                payload.put("aps", this.apsDictionary);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public PushNotificationPayloadExtends(String alert, int badge, String sound)
            throws JSONException
    {
        this();
        if (alert != null) {
            addAlert(alert);
        }
        addBadge(badge);
        if (sound != null) {
            addSound(sound);
        }
    }

    public void addBadge(int badge)
            throws JSONException
    {
        logger.debug("Adding badge [" + badge + "]");
        put("badge", Integer.valueOf(badge), this.apsDictionary, true);
    }

    public void addSound(String sound)
            throws JSONException
    {
        logger.debug("Adding sound [" + sound + "]");
        put("sound", sound, this.apsDictionary, true);
    }

    public void addAlert(String alertMessage)
            throws JSONException
    {
        String previousAlert = (String)getCompatibleProperty("alert", String.class, "A custom alert (\"%s\") was already added to this payload");
        logger.debug("Adding alert [" + alertMessage + "]" + (previousAlert != null ? " replacing previous alert [" + previousAlert + "]" : ""));
        put("alert", alertMessage, this.apsDictionary, false);
    }

    private JSONObject getOrAddCustomAlert()
            throws JSONException
    {
        JSONObject alert = (JSONObject)getCompatibleProperty("alert", JSONObject.class, "A simple alert (\"%s\") was already added to this payload");
        if (alert == null)
        {
            alert = new JSONObject();
            put("alert", alert, this.apsDictionary, false);
        }
        return alert;
    }

    private <T> T getCompatibleProperty(String propertyName, Class<T> expectedClass, String exceptionMessage)
            throws JSONException
    {
        return (T)getCompatibleProperty(propertyName, expectedClass, exceptionMessage, this.apsDictionary);
    }

    private <T> T getCompatibleProperty(String propertyName, Class<T> expectedClass, String exceptionMessage, JSONObject dictionary)
            throws JSONException
    {
        Object propertyValue = null;
        try
        {
            propertyValue = dictionary.get(propertyName);
        }
        catch (Exception e) {}
        if (propertyValue == null) {
            return null;
        }
        if (propertyValue.getClass().equals(expectedClass)) {
            return (T)propertyValue;
        }
        try
        {
            exceptionMessage = String.format(exceptionMessage, new Object[] { propertyValue });
        }
        catch (Exception e) {}
        throw new PayloadAlertAlreadyExistsException(exceptionMessage);
    }

    public void addCustomAlertBody(String body)
            throws JSONException
    {
        put("body", body, getOrAddCustomAlert(), false);
    }

    public void addCustomAlertTitle(String title)
            throws JSONException
    {
        put("title", title, getOrAddCustomAlert(), false);
    }

    public void addCustomAlertSubtitle(String subtitle)
            throws JSONException
    {
        put("subtitle", subtitle, getOrAddCustomAlert(), false);
    }


    public void addCustomAlertActionLocKey(String actionLocKey)
            throws JSONException
    {
        Object value = actionLocKey != null ? actionLocKey : new JSONNull();
        put("action-loc-key", value, getOrAddCustomAlert(), false);
    }

    public void addCustomAlertLocKey(String locKey)
            throws JSONException
    {
        put("loc-key", locKey, getOrAddCustomAlert(), false);
    }

    public void addCustomAlertLocArgs(List args)
            throws JSONException
    {
        put("loc-args", args, getOrAddCustomAlert(), false);
    }

    public int getMaximumPayloadSize()
    {
        return 256;
    }

    void verifyPayloadIsNotEmpty()
    {
        if (getPreSendConfiguration() != 0) {
            return;
        }
        if (toString().equals("{\"aps\":{}}")) {
            throw new IllegalArgumentException("Payload cannot be empty");
        }
    }
}
