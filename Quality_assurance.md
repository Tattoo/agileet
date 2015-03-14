# Introduction #
Quality assurance shall be achieved in the Pokkare project by means of several direct and undirect procedures. Direct procedures include testing, coding standards and peer reviews etc. Undirect procedures concern aspects of the software development process. Some of the quality aspects of eXtreme Programming (XP), modified to fit the special circumstances of this project, are chosen to complement Scrum in order to achieve better quality.

# Direct procedures #


## Unit testing ##
The target is to have comprehensive JUnit test cases of the system, covering at least 90% of the code. Integration shall be done as often as possible, leaving it to developers discretion.

## Functional testing ##
A release candidate is compiled weekly before the practice meeting. For the release candidate, testing the functionality as well as running the JUnit tests is mandatory. If the release candidate is found defunct in the meeting, it is not accepted and shall be fixed before the release can be made. Functional tests are written by the development team according to the requirement specification. All test must pass before practice meeting; otherwise release candidate is made from a revision that did pass all of it's tests.

## Coding standards ##
Common coding standards shall be used throughout the project. Standard Java codestyle and best practices shall be used as per Eclipse IDE standard behaviour. Code shall be commented where functionality is not clear or it is otherwise needed. Code should be written to adhere to the fail-fast principle to improve testing.

Refactoring of the code is done as per XP guidelines: when refactoring helps adding of new functionality or if after implementing the functionality an easier way to accomplish it emerges. The decision to refactor code is not based on the programmers speculation, rather on reacting to the state of the system.

Developers are responsible for unit testing their own code, writing and running JUnit tests and documenting functional tests. TDD is required. The code is collectively owned.

It is encouraged to debug hard problems together.

## Peer reviews ##
Functionality shall be reviewed by the course practice group weekly. Added and/or changed functionality shall be reviewed internally by the team before issues (be it tasks or fixes) are accepted and closed.

## Issue tracking ##
Issues shall be tracked using the code.google.com issue tracker. Issue workflow is:
  1. When an issue arises, it is documented to the issue tracking system by the person who thought it up. It is not assigned to any iteration milestone (`*`). Issue is labelled _"New"_
  1. In Sprint planning meeting, issues are assigned for the next sprint. Some issues could be tentatively assigned to future sprints, but these are not final until the planning meeting of the sprint in question.
  1. At any given point, anybody can comment the issue and thus give his input.
  1. Issue is marked _"Accepted"_ when the issue owner acknowledges the issue, ie. he knows what the issue is about and what needs to be done.
  1. When the owner of the issue is decided that the issue has been handled properly (see Definition of done), he communicates to other developer team member so the issue can be reviewed. Alternatively he can mark the issue with labels such as _"WontFix"_
  1. Verification of other developer team member is needed to fully close the issue. The reviewer marks the issue _"Verified"_.
  1. If the reviewer sees that the issue is not fully complete and decides to handle the rest himself (this approach is encouraged since all work is collectively shared), then the reviewer temporarily becomes issue owner while the original issue owner needs to review all changes.
  1. In Sprint demo meeting, all issues of the sprint are reviewed.

(`*`): There is one exception: If during the sprint it emerges that the issue is something else than originally thought, it is allowed to close the original issue and create new issue. Notice that it is demanded that the new issue can be completed in the time frame reserved for the original issue.

### Definition of done ###
All tasks need to have:
  * Unit tests that pass (if applicable)
  * Unit tests attached as part of the test suite which passes (if applicable)
  * Proper documentation in proper place.
    1. Issue tracking
    1. Wiki
    1. Code comments
    1. IRC logs/mail messages
  * Committed to version control (if applicable). If something that should be in version control byt isn't, it doesen't exist.
  * Issue in issue tracking labeled informatively ("Fixed", "Closed", "WontFix", etc.)
  * Issue in issue tracking to be commented how it was resolved by owner.
  * Issue in issue tracking is labeled _"Verified"_ and commented by other developer team member to show the task resolution was reviewed.


## Documentation ##
Documentation of Pokkare shall be done according to agile conventions: whenever and only whenever it is needed. Documentation exists primarily for the benefit of the development team. Major documentation is the code where a javadoc is produced. Other documentation, such as class diagrams and other developer-to-developer -documentation, is collected to project wiki at code.google.com .

## Project metrics tracking ##
Primary metrics in the project is finished functionality and open issues. Measurements according to these metrics shall be executed per sprint in Sprint demo meeting.

Teams velocity is used as the secondary metrics. Velocity is calculated by adding up the work hour estimates for each task accomplished in a sprint. Early velocity figures are educated guesses.

Other metrics include functional test results, number of faults and test cases added as result to spotting faults and the number of refactorings to make the system more robust. [CK-metrics(\*)](http://www.aivosto.com/project/help/pm-oo-ck.html) is used to track progam complexity.

(`*`): Exception to this is _Lack of Cohesion on Methods_-metric that is encouraged to not use.

# Indirect procedures #
A modified XP process shall be used to ensure quality within the Scrum process. The following remarks describe the quality driven process.

## Releases ##
Early delivery of a partially functioning system will happen, since this has been shown to correlate with quality.

Subsequent releases are weekly, thus steering the project is possible early. A release is made every week for the practice group meeting ("the customer") in a Sprint demo meeting.

A Sprint planning meeting is also held at the practice group weekly meeting.

## Customer ##
The project has a "real customer" (and the project is a real product). The product is reviewed by customer. The practice group meeting will be responsible for prioritizing the functionality for each release/sprint according to the project owner's suggestions.

## User stories ##
User stories of additional functionality shall be used and documented in the code.google.com wiki as issues (tasks) and later included in the requirement specification as fleshed-out, concrete use cases. Releases shall be planned according to these user stories. The stories shall be prioritized. The team's velocity, or how much the team accomplished, is measured after each sprint.

## Metaphors ##
Metaphors are used to help the developers and peers at the practice group to grasp the system functionality. Main metaphors include the MVC model and user stories documented in the requirement specification. Metaphors that describe problem domain shall form a projects glossary, which will be documented in the wiki when needed.

## Resources ##
Adequate resources in the scope of the project are provided by the university. Team members are required to provide their own development related equipment. Scrace resources need to be considered at all times when prioritizing functionality and/or issues.

## Release planning ##
Release planning is done according to task priorities, customers wishes, team's velocity and program's state. Release planning is done at the weekly practice group meetings. Teams velocity, task estimates, their priorization, the practice groups wished as both the customer and the peer group shall be taken into account when planning the next sprints task list.

![http://www.extremeprogramming.org/map/images/iteration.gif](http://www.extremeprogramming.org/map/images/iteration.gif)

## Spikes ##
Spikes can be used where applicable. Spikes are very simple discardable sample programs which demonstrate the use of a functionality. They are useful for exploring of potential solutions to technical or design problems. Mockups should be preferential to actual code.

## No overtime ##
The team shall do no overtime. If all intended functionality in a sprint cannot be accomplished, it is left out of the release. This is referenced to as _limitng the scope_. Deadlines will not be postponed. The peer group and customer chooses what needs to be done according to the missing functionality at the next release planning meeting. It can be put to the next releases' task list, it can be modified or even omitted if not applicable anymore.


## Self organized team ##
The team will decide it's own workflows and modify existing workflows when they are found to be inadequate. This includes also this document.

## Communication is essential ##
Shared workspace is simulated through constant communication via IRC. All matters need to be discussed with other developer team members. All work is reviewed.

## Simple design is practiced ##
Design should follow the principle of **Keep It Simple, Soldier**. Design should be refactored to adhere KISS.