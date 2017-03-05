# gh-utils

[![Clojars Project](https://img.shields.io/clojars/v/gh-utils.svg)](https://clojars.org/gh-utils)
[![Dependencies Status](https://jarkeeper.com/agilecreativity/gh-utils/status.svg)](https://jarkeeper.com/agilecreativity/gh-utils)

Clojure library designed to automate some useful git command without the need to
use the web browser. Based on the [tentacles][] library with sensible default.
You can simply create new Github repo with one simple command and one config file.
TL;DR; `gh-utils --config ./Dropbox/github.edn --repo my-awesome-idea`

### Installation and usage as command line interface (CLI)

#### Pre-requisites

- [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Leiningen](http://leiningen.org/#install)

#### Installation

```sh
# Clone this repository locally
mkdir -p ~/projects

git clone https://github.com/agilecreativity/gh-utils.git ~/projects/gh-utils

cd ~/projects/gh-utils

# Create the `~/bin` folder to hold the executable
mkdir -p ~/bin

# Generate the standalone binary using `lein bin`
lein bin
```

#### Usage

You will need to create the simple [edn](https://github.com/edn-format/edn) configuration
file. The file can have any name but the format must be something like:

I personally store this config file in the following location `~/Dropbox/github.edn`

```clj
{:username "YOUR-GITHUB-ID"
 :password "YOUR-GITHUB-PASSWORD"}
```

- To create a repository called `my-awesome-idea` (private by default) try

```sh
# The long version
gh-utils --config ~/Dropbox/github.edn --repo my-awesome-idea

# Or shorter version
gh-utils -c ~/Dropbox/github.edn -r my-awesome-idea
```

- If you already inside the root directory of the project and the file `config.edn` is
in already in the same directory that you are running this command then

```sh
mkdir -p ~/codes/my-awesome-idea
cd ~/codes/my-awesome-idea

# Just run the command without any argument
gh-utils

# To create your repository in Github and push your changes to remote branch try
gh-utils -c ~/path/to/github-config.edn -r my-awesome-id -p

# Note without supplying -p the repository will be created remotely without initial git push
```

Now open `https//github.com/YOUR-GITHUB-ID/my-awesome-idea` in your browser.

### Development

- Leiningen

```clj
[org.clojars.agilecreativity/gh-utils "0.2.0"]
```

### Related Links

- The original [tentacles](http://raynes.github.io/tentacles/) documentation
- My [forked version][] of tentacles library with newer version of [clj-http][]
- Ways people are trying to address this issue on [Stack Overflow](http://stackoverflow.com/questions/2423777/is-it-possible-to-create-a-remote-repo-on-github-from-the-cli-without-opening-br)
- [clojure.core/re-pattern](https://clojuredocs.org/clojure.core/re-pattern)
- [clojure.core/replace](https://clojuredocs.org/clojure.string/replace)

### Roadmaps & TODOs

- [x] Add the intial commit with README.md file `touch README.md && git add README.md && git commit -m "Add README.md" && git push"`
- [x] Make use of [clj-jgit](https://github.com/clj-jgit/clj-jgit)
- [ ] Allow override of default options like ':public', ':description', etc

### License

Copyright © 2016-2017 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

---

[tentacles]: http://github.com/Raynes/tentacles.git
[clj-http]: https://github.com/dakrone/clj-http
[forked version]: https://github.com/agilecreativity/tentacles.git
