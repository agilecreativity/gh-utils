# gh-utils

Clojure library designed to automate some useful git command without the need to
use the web. Based on the [tentacles][] library

## Usage

Since the version of [tentacles][] contain the bugs with [clj-http][], the workaround
involve using local version of the `project.clj` e.g.

```sh
mkdir -p ~/codes && cd ~/codes
git clone git@github.com:Raynes/tentacles.git
cd tentacles
# update `clj-http` from 1.0.1 to 2.0.1
# And change version to new version `defproject tentacles "0.5.2"`
# Then publish the library locally
lein install
```

## License

Copyright © 2016 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

---

[tentacles]: http://github.com/Raynes/tentacles.git
[clj-http]: http:/clojure.com/
