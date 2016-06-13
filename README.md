# gh-utils

Clojure library designed to automate some useful git command without the need to
use the web browser. Based on the [tentacles][] library with sensible default.

### Installation and usage as command line interface (CLI)

#### Installation

```sh
# Clone this repository locally
mkdir -p ~/projects

git clone https://github.com/agilecreativity/gh-utils.git ~/projects/gh-utils

cd ~/projects/gh-utils

# Create the `~/bin` folder to hold the executable
mkdir -p ~/bin

# Generate the standalone using `lein bin`
lein bin
```

#### Usage

You will need to create the provide the credential in the the file called `config.edn`
in the following format:

```clj
{:username "YOUR-GITHUB-ID"
 :password "YOUR-GITHUB-PASSWORD"}
```

- To create a repository called `my-awesome-idea` (private by default) try

```sh
# The long version
gh-utils --config config.edn --repo my-awesome-idea

# Or shorter version
gh-utils -c config.edn -r my-awesome-idea
```

- If you already inside the root directory of the project and the file `config.edn` is
in already in the same directory that you are running this command then

```sh
mkdir -p ~/codes/my-awesome-idea
cd ~/codes/my-awesome-idea

# Create the new Github repository of the name 'my-awesome-idea' in Github
gh-utils
```

Now open `https//github.com/YOUR-GITHUB-ID/my-awesome-idea` in your browser.

### Development

- Leiningen

```clj
[org.clojars.agilecreativity/gh-utils "0.1.0"]
```

### Links

- The original [tentacles](http://raynes.github.io/tentacles/) documentation
- My [forked version](https://github.com/agilecreativity/tentacles) with newer version of [clj-http][]
- Ways people are trying to address this issue on [Stack Overflow](http://stackoverflow.com/questions/2423777/is-it-possible-to-create-a-remote-repo-on-github-from-the-cli-without-opening-br)

### TODOs

- [ ] Add the intial commit with README.md file `touch README.md && git add README.md && git commit -m "Add README.md" && git push"`
- [ ] Make sure we can load configuration from any directory e.g. `github-new-repo -c ../some/path/config.edn` should work

### License

Copyright © 2016 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

---

[tentacles]: http://github.com/Raynes/tentacles.git
[clj-http]: https://github.com/dakrone/clj-http
