# App Clima

Simple app that retrieves nearby cities based on a point in the map

The packages are splitted by layer mainly, besides `feature` that is spliited by feature.
This approache was used to simulate multi modules, where `repository`, `model` could be simple kotlin or android modules (depending the dependencies they ended up module having), and the `feature` module is the app itself
I haven't really created all the modules because I think it goes out of scope and also needs extra boilerplate code for the setup (DI and so on)


The communication between layer should follow:
View -> ViewModel -> Repository -> DataSouces

It would be possible to add an extra layer between ViewModel and Reopsitory, if a feature needs to interact with many repositories. Not all scenarios are needed thought.