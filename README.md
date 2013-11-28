Overview
==

This repository contains a simple Java example of how to run [SPT](http://github.com/VolkerL/spt) testcases using [Sunshine](http://github.com/VolkerL/spoofax-sunshine).

Installation
===

To use this example, please consult the [SPT Readme](http://github.com/VolkerL/spt/tree/sunshine-port/README.md)
on how to build both SPT and Sunshine.

Once you managed to build them both, you can alter this repository's `Main.main` method to initialize Sunshine with paths that make sense for your setup.
Note that this project requires the `sunshine.jar` that you made during the building of Sunshine.

Usage
===

Just run `Main.main` and check out the cool JSON report in the console.
It is pretty straightforward to expand this example and atually do interesting stuff with the `TestSuite` report.
