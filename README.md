# VoteLogger

A software for logging votes, works with Votifier.

## Requirements

* Minecraft 1.13.2
* Votifier or NuVotifier
* JRE/JDK 8

## Install

1. Install Votifier or **NuVotifier**. NuVotifier is recommended.
1. Install VoteLogger.
1. Test your vote system at [Votifier Tester](https://mctools.org/votifier-tester).
1. Open `plugins/VoteLogger/vote.log` and check these test(real) votes are properly logged.

## Commands

`/votelogger help` or `/vl help` to see the reference.

### help

`/vl help` to see plugin's help.

### version

`/vl version` or `/vl ver` to see plugin's version.

### log

`/vl log [serviceName] [username] [address]` to write test record to vote log. No arguments is required. `serviceName` is a provider, `username` is Minecraft ID, and `address` is an address of the provider.

## License

```text
VoteLogger: A software for logging votes, works with Votifier.
Copyright (C) 2018 akaregi <akg.tachibana@gmail.com>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 ```
