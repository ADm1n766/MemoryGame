# Grundüberlegungen / Architektur
- MVC
Model: 
- game logic + state (no swing-components)
- fire PorpertyChanges for updating the view
View: 
- only swing components & methods for updating view
Controller: 
- connects view and model
- responds to click, calls model operations
App:
- assembles MVC, starts GUI on EDT (Event Dispatch Thread)