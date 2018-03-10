package com.ktar5.utilities.libgdx.fsm;

public class FSMtester {

    public static void main(String[] args) {
        new FSMtester();
    }

    public enum Triggers {
        AAA, BBB, CCC, DDD, EEE;
    }

    public State1<Triggers, String> trigger1;
    public State<Triggers> AAA = new State0<>(Triggers.AAA);

    public FSMtester() {
        GameStateMachine<State<Triggers>, Triggers> fsm = new GameStateMachine<State<Triggers>, Triggers>(AAA) {
            @Override
            public Triggers getTriggerFrom(State<Triggers> state) {
                return state.getTrigger();
            }
        };
        trigger1 = new State1<Triggers, String>(Triggers.BBB, fsm.configuration()) {
        };
        fsm.configuration().configure(AAA).permit(Triggers.BBB, trigger1);
        fsm.configuration().configure(AAA).ignore(Triggers.AAA);
        fsm.configuration().configure(trigger1).ignore(Triggers.BBB);
        fsm.configuration().configure(trigger1).permit(Triggers.AAA, AAA);
        fsm.fire(trigger1.paraTrigger, "Test");
    }


}
