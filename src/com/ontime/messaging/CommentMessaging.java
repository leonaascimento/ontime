package com.ontime.messaging;

import org.springframework.stereotype.Component;

import com.ontime.viewmodel.CommentSummaryViewModel;
import com.pusher.rest.Pusher;

@Component
public class CommentMessaging {
	
	private Pusher pusher;
	private final String appId = "237083";
	private final String apiKey = "1903bfdf2d61093ea14d";
	private final String apiSecret = "f7a3becb1b5393e391df";
	
	public CommentMessaging() {
		pusher = new Pusher(appId, apiKey, apiSecret);
	}
	
	public void broadcastCreated(CommentSummaryViewModel comment) {
		pusher.trigger("comments-channel", "new_comment", comment);
	}

}
