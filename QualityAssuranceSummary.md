# Introduction #

Project followed [this process](Process.md) and [this quality assurance plan](Quality_assurance.md). This document reviews how these worked and didn't work in relation to the product on Friday 24th of April 2009, the end day of the project.

# Synopsis #

Following quality goals were met:
  1. Unit testing has over 90% coverage and they all pass. This can be verified by running AllTests -test suite with [EclEmma-plugin](http://eclemma.org/installation.html) in Eclipse.
  1. Release candidate was done weekly where all functionality was tested. Release candidate was successful every time, which was a nice bonus..
  1. Code was heavily refactored to adhere standard Java codestyle.
  1. Refactoring was done heavily and CK-metrics provided framework to understand the quality of refactoring.
  1. Project was peer-reviewed weekly. Pretty much all issues[`1`] were internally reviewed.
  1. All issues were tracked in issue control system and was resolved one way or another. These were reviewed and customer should be aware of all decision on all issues.
  1. Other documentation was updated and reflects the current state of the program.
  1. Target platforms requirement (from [requirement specification](Requirement_specification.md) Opera 9.64 and Firefox 3.0.7 on Windows XP Service pack 3, Ubuntu Linux 8.10, Mac OS X Tiger (10.4)) was met.

[`1`]: Some issues from before this demand came to effect were not

Following quality goals were not met:

  1. Javadoc wasn't produced since documentation of code was lacking. Code doesen't have general documentation (eg. "what is purpose of class?").
  1. No documentation to objectively show what CK-metrics say about the quality of code.


Notes:
  * **About quality problem 1**: On the other hand, code is clearly documented when it comes to questions about design decision made during implementation.

# Personal comments #

## Tatu ##

In general, I think the process and the quality plan worked well. There was **practically** no need to adjust the plans after they were first done. The process supported our way of working.

The hardest part about this project was discipline. Many times the thought "_this is just a course project_" creeped into mind when it was a matter of cranking a few more tests or procrastinating. It helped that you had to show what you did to other team members.

Secondly, working remotely together is harder. Our process dropped a lot of agile methods relating to working together from our process and while it worked -- just and just, I feel -- for two people in a project this size, it is evident that eg. shared workspace and working same hours is very essential for efficient communication.

Third thing is, while I do believe our work is quite good qualitatively, we do not how much to show it if objective demonstration is needed. Our project could've benefitted from more metrics. On the other hand, it is clearly evident that eg. working hours is useless metric for our project[`1`], so the metrics we would (in imaginary future) choose, need to be carefully selected.

[`1`]: Since the project is about doing X hours per fixed 7 days, it is of no use to measure that. Separate issue is to measure how many issues were done in the sprint, or why some issue wasn't done.

## Joonas ##

We chose to refactor and develop further an unfinished project of mine. I think this turned out to be a good choice for our project team (consisting of only two people) for several reasons. For one, we (or I, at least) knew what the program's current, missing and faulty functionality was, so the specification of said functionality was easy to do: we knew which product we were building.

Secondly we were familiar with the developing tools and frameworks. I was mainly working with the back end and Tatu the front end. This was something we were both comfortable with and something that came naturally rather than a recognized choice. Being familiar with the required tools and frameworks resulted in minimal need of "staff education" throughout the project.

Thirdly, and I think this might be the most important point, we were motivated and excited to be working on this kind of project throughout the course. We also had good infrastructure to back up our work, Most importantly google code and efficient communication means like irc etc.

Our process was a combination of ad hocary, scrum guidelines and all kinds of best practices (such as the XP practices, and issue tracking for example). The process suffered from the usual problems in distributed agility: communication and lack thereof. Also as there was noone to enforce the disciplinary sides of the process, for example, TDD and correct issue tracking practices, we did not always follow them to the letter. Still, I believe we did good in this sector too. We met our quality targets and ended at 90+% code coverage in the end, which was one of the most important important quality targets.

Main metrics were chosen to be accomplished functionality. This worked well for us. As we did spend most of our time in developing the software and writing theme tasks, not much time, or "velocity" was left to spend on the kind of process related work such as metrics and measuring. This was no problem, because we did not run into any serious issues.

If we actually continued to develop the program further in this manner, at some point we would most likely end up in a situation where neglecting the process related work would cost us working hours. This being said, I think our process was well suited for this project. In the end, we can measure our main metrics at 100%, which is something we can be proud of.