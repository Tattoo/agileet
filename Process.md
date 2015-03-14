# Introduction #

A modified Scrum process seasoned with best practices à la XP will be used in development and project management. This document defines the process.

# Roles #

Defines the roles and responsibilities in the process.

## Product Owner ##
The Product Owner represents the voice of the customer. They ensure that the Scrum Team works with the right things from a business perspective. The Product Owner writes user stories, prioritizes them, then places them in the product backlog.

Joonas

## ScrumMaster ##
Scrum is facilitated by a ScrumMaster, whose primary job is to remove impediments to the ability of the team to deliver the sprint goal. The ScrumMaster is not the leader of the team (as they are self-organizing) but acts as a buffer between the team and any distracting influences. The ScrumMaster ensures that the Scrum process is used as intended. The ScrumMaster is the enforcer of rules.

Shared between Joonas and Tatu

## Team ##
The team has the responsibility to deliver the product.

Joonas & Tatu

## Chicken roles ##
Chicken roles are not part of the actual Scrum process, but must be taken into account. An important aspect of an Agile approach is the practice of involving users, business and stakeholders into part of the process. It is important for these people to be engaged and provide feedback into the outputs for review and planning of each sprint.

The practice group
Department of Computer Science

### Users ###
The software is being built for someone.

Joonas.

# Meetings #

## Daily Scrum ##
Developer team is simulating shared worspace through IRC. A quick meeting is held every time a developer starts working on something and other developers are present at IRC and capable of sharing.  During the meeting, each team member answers three questions:
  1. What have you done since last check?
  1. What are you planning to do next?
  1. Do you have any problems preventing you from accomplishing your goal?

## Sprint Planning Meeting ##
At the beginning of the sprint cycle (every Friday), a Sprint Planning Meeting is held. Sprint backlog is reviewed and re-priotized by practice group as customer and Tatu + Joonas as development team.

## Sprint Review Meeting ##
At the end of a sprint cycle (every Friday), two meetings are held: the Sprint Demo Meeting and the Sprint Retrospective.

In the review, the work that was completed and not completed is reviewed and the completed work (known as _release candidate_) presented to the stakeholders (a.k.a. "the demo")

Some time after the practice group meeting, development team members have a quick retrospective, where all team members reflect on the past sprint. Two main questions is answered:
  1. What went well during the sprint?
  1. What could be improved in the next sprint?

# Artifacts #

## Product backlog ##

The product backlog is the issue tracking at Google Code-project page (http://code.google.com/p/agileet/issues/list)

The product backlog is property of the Product Owner. Business value is set by the Product Owner. Development effort is set by the Team.

In the Sprint demo and Sprint planning meetings, issues can be moved out of the Product backlog by the practice group and product owner. In real-life, they are moved to "obsolete"-milestone and labeled accordingly with comments to show when the issue was decided.

No issue can ever be removed entirely from the Product backlog.

## Sprint backlog ##

Since the project has fixed, one-week sprints, the sprint backlog is the issue tracking at Google Code-project page containing only the issues that are labeled to belong to the sprint at hand. Sprint backlog is hence subset of Product backlog

Issues on the sprint backlog are never assigned; rather, tasks are signed up for by the team members as needed, according to the set priority and the team member skills.

The sprint backlog is property of the Team. Estimations are set by the Team.

# Project management #

Project has weekly deliveries with working functionality, like all other forms of agile software processes. This enables the customer to get working software earlier and enables the project to change its requirements according to changing needs.

Frequent risk and mitigation plans are developed by the development team itself—risk mitigation, monitoring and management (risk analysis) occurs at every stage and with commitment.

No problems are swept under the carpet. No one is penalized for recognizing or describing any unforeseen problem.

Workplaces and working hours must be energized. Each team member is responsible to monitor their own wellbeing _as well as_ each of other team members.


# Quality assurance #

To ensure ever-increasing quality, the _**[Quality Assurance Plan](Quality_assurance.md)**_ must be internalized by all team members and followed at all times.  This document also describes everyday development practices adopted from XP that need to be used.


# Terminology #

The following terminology is used in the project:

## Roles ##

Roles describe different roles in the project.

### Product Owner ###

The person responsible for maintaining the Product Backlog by representing the interests of the stakeholders.

### ScrumMaster ###

The person responsible for the Scrum process, making sure it is used correctly and maximizes its benefits.

### Team ###
A cross-functional group of people responsible for managing itself to develop the product.

## Artifacts ##

Artifacts describe important concepts all project memebers need to know.

### Product backlog ###
The product backlog is a high-level document for the entire project. It contains issues: broad descriptions of all required features, wish-list items, defects, etc. prioritised by business value. It is the "What" that will be built. It is open and editable by anyone and contains at least rough estimates of both business value and development effort.

### Sprint backlog ###
A list of issues to be completed during the sprint at hand.

### Release candidate ###
The working software that demonstrated what was and was not completed.

## Others ##

### Sprint ###
A time period (in this project, one week (8 days) from Friday to Friday, inclusive) in which development occurs on a set of backlog items that the Team has committed to.

[Source: Wikipedia](http://en.wikipedia.org/wiki/Scrum_(development))