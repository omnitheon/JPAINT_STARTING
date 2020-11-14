package model;

import model.interfaces.IShapeListObserver;
import model.interfaces.IObserver;
import model.interfaces.ISubject;
import java.util.ArrayList;
import java.util.List;

public class StateChangeHandler implements ISubject, IShapeListObserver {
    private static final StateChangeHandler SCH = new StateChangeHandler();
    private StateChangeHandler() {}
    public static StateChangeHandler getInstance(){
        return SCH;
    }
    private List<IObserver> observers = new ArrayList<>();
    @Override public void registerObserver(IObserver observer) { observers.add(observer); }
    @Override public void removeObserver(IObserver observer) { observers.remove(observer); }
    private void notifyObservers(){ for(IObserver observer : observers) observer.update(); }
    @Override public void update() { 
        System.out.println("[STATE HANDLER] update executed..notifying observer");
        notifyObservers(); 
    
    }
}

