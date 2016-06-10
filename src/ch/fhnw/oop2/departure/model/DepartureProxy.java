package ch.fhnw.oop2.departure.model;

public class DepartureProxy extends Departure {
	private Departure currentDeparture;
	public void set(Departure newDeparture){
		if(currentDeparture !=  null){
			idProperty().unbindBidirectional(currentDeparture.idProperty());
			departureTimeProperty().unbindBidirectional(currentDeparture.departureTimeProperty());
			trainNumberProperty().unbindBidirectional(currentDeparture.trainNumberProperty());
			destinationProperty().unbindBidirectional(currentDeparture.destinationProperty());
			viaProperty().unbindBidirectional(currentDeparture.viaProperty());
			platformProperty().unbindBidirectional(currentDeparture.platformProperty());
		}
		if(newDeparture != null){
			idProperty().bindBidirectional(newDeparture.idProperty());
			departureTimeProperty().bindBidirectional(newDeparture.departureTimeProperty());
			trainNumberProperty().bindBidirectional(newDeparture.trainNumberProperty());
			destinationProperty().bindBidirectional(newDeparture.destinationProperty());
			viaProperty().bindBidirectional(newDeparture.viaProperty());
			platformProperty().bindBidirectional(newDeparture.platformProperty());
			
		}
		currentDeparture = newDeparture;
	}

}
