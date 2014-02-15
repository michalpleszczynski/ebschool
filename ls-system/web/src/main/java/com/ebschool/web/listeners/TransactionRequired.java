package com.ebschool.web.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * User: michau
 * Date: 1/19/14
 * Time: 8:00 PM
 */
public class TransactionRequired implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        System.out.println("END PHASE " + phaseEvent.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent){
        System.out.println("START PHASE " + phaseEvent.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
