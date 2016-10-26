/*
 * Copyright (C) 2013 by danjian <josepwnz@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.coinbot.utils;

import java.util.Timer;
import java.util.TimerTask;


public abstract class CountdownTimer implements Runnable {
	private boolean stopped = true;
	private int hours;
	private int minutes;
	private int seconds;
	
	public CountdownTimer() {
		this(0, 0, 0);
	}
	
	public CountdownTimer(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public int getHours() {
		return hours;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public String toString() {
		String h = (hours < 10) ? "0" + hours : "" + hours;
		String m = (minutes < 10) ? "0" + minutes : "" + minutes;
		String s = (seconds < 10) ? "0" + seconds : "" + seconds;
		return h + ":" + m + ":" + s;
	}
	
	public void start() {
		stopped = false;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		stopped = true;
	}
	
	public boolean hasFinished() {
		return stopped;
	}
	
	public abstract void timerTask();
	
	@Override
	public void run() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if(stopped) {
					t.cancel();
					return;
				}
				
				seconds--;
				if(seconds < 0) {
					minutes--;
					seconds = 59;
				}
				
				if(minutes < 0) {
					hours--;
					minutes = 59;
				}
				
				if(hours < 0) {
					stopped = true;
				}
				timerTask();
			}
		}, 0, 1000);
	}
}
