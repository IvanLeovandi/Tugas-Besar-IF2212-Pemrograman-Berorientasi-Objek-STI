package com.simplicity.Interfaces.Listeners;

import com.simplicity.Events.HousePickEvent;

public interface HousePickListener extends SimplicityListener {
    public void onHousePick(HousePickEvent e);
}
