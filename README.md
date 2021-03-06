## A simple Game of Life implementation written in Scala.js



This is a simple [Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) implementation written in
[Scala.js](https://www.scala-js.org/), using the [scala-js-example-app](https://github.com/sjrd/scala-js-example-app).

## Get started

To get started, open `sbt` in this example project, and execute the task
`fastOptJS`. This creates the file `target/scala-2.11/example-fastopt.js`.
You can now open `index-fastopt.html` in your favorite Web browser!

During development, it is useful to use `~fastOptJS` in sbt, so that each
time you save a source file, a compilation of the project is triggered.
Hence only a refresh of your Web page is needed to see the effects of your
changes.

## Run the tests

To run the test suite, execute the task `test`. If you have installed
[Node.js](http://nodejs.org/), you can also run `fastOptStage::test` which is
faster.

## The fully optimized version

For ultimate code size reduction, use `fullOptJS`. This will take several
seconds to execute, so typically you only use this for the final, production
version of your application. While `index-fastopt.html` refers to the
JavaScript emitted by `fastOptJS`, `index.html` refers to the optimized
JavaScript emitted by `fullOptJS`.

## Todo

* cells change color with age
* check only cells and their immediate neighborhood, not the entire min-max range
* switch rules on key press
* timing/benchmarking
* save to file on key press (creating brushes on pause)
* load saved state and use as brush
* add a scalajs.fiddle version in one file