# Change Log

All notable changes to this project will be documented in this file. This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [Unreleased]

## [0.3.9] - 2019-02-23

- Use latest version of lein-binplus that work properly with Clojure 1.10.0
- Bump other dependencies to the most recent version 

## [0.3.8] - 2018-08-24

- Use maintained version of fs 
- Minor code cleanup

## [0.3.7] - 2018-08-21

- Update project dependencies

## [0.3.5] - 2018-03-04

- Explicitly exit at the end

## [0.3.4] - 2018-03-04

- Read default config from `~/Dropbox/config/github.edn`

## [0.3.3] - 2018-03-01

- Read configuration using aero
- Use lein-binplus instead of lein-bin
- Minor code cleanup

## [0.3.2] - 2017-12-11

- Bump version
- Use clojure version 1.9.0

## [0.3.1] - 2017-12-01

- Use maintained version of `tentacles` library
- Bump version
- Update project dependencies

## [0.3.0] - 2017-07-02

### Change

- Make `--repo` mandatory
- Better check for `.git` directory

## [0.2.1] - 2016-03-05

### Added

- Add options to allow `--remote-label` default to `origin`
- Make `--init-commit` commit as options

## [0.2.0] - 2016-03-05

### Added

- Allow the creation of initial commit and push to remote branch
- Minor APIs cleanup

## [0.1.2] - 2016-07-24

### Changed

- Read default configuration file from `~/Dropbox/github.edn`
- Improve documentation and fix change logs

### Added

- Show `git remote add origin https://github.com/USER/new-repo.git`

## [0.1.1] - 2016-07-11

### Added

- Initial public release

## [0.1.0] - 2016-06-13

### Added

- Initial commit

### Added

- Initial working version

[Unreleased]: https://github.com/agilecreativity/gh-utils/compare/0.3.5...HEAD
[0.3.5]: https://github.com/agilecreativity/gh-utils/compare/0.3.4...0.3.5
[0.3.4]: https://github.com/agilecreativity/gh-utils/compare/0.3.3...0.3.4
[0.3.3]: https://github.com/agilecreativity/gh-utils/compare/0.3.2...0.3.3
[0.3.2]: https://github.com/agilecreativity/gh-utils/compare/0.3.1...0.3.2
[0.3.1]: https://github.com/agilecreativity/gh-utils/compare/0.3.0...0.3.1
[0.3.0]: https://github.com/agilecreativity/gh-utils/compare/0.3.0...0.2.1
[0.2.1]: https://github.com/agilecreativity/gh-utils/compare/0.2.0...0.2.1
[0.2.0]: https://github.com/agilecreativity/gh-utils/compare/0.1.2...0.2.0
[0.1.2]: https://github.com/agilecreativity/gh-utils/compare/0.1.1...0.1.2
[0.1.1]: https://github.com/agilecreativity/gh-utils/compare/0.1.0...0.1.1
