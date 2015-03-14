# Introduction to CMMI #

**CMMI** (Capability Maturity Model Integration) is follower to  Capability Maturity Model (**CMM**), which was initially created as an abstract model for the military to use as an objective evaluation of software subcontractors.

![http://upload.wikimedia.org/wikipedia/commons/6/64/Characteristics_of_the_Maturity_levels.jpg](http://upload.wikimedia.org/wikipedia/commons/6/64/Characteristics_of_the_Maturity_levels.jpg)

CMMI is a collection of best practices that meet the needs of organizations in different areas of interest. A collection of best practices that cover a particular area of interest is called a _CMMI model_. These models are:
  1. _Development_, addresses product and service development processes.
  1. _Acquisition_, addresses supply chain management, acquisition, and outsourcing processes in government and industry.
  1. _Services_, addresses guidance for delivering services within an organization and to external customers.

All CMMI models contain multiple Process Areas (**PA**). A PA has 1 to 4 goals, and each goal is comprised of practices which describe specific activities in each PA. An additional set of (generic) goals and practices applies across all of the process areas.


# Agile processes & CMMI #

General problem is to map the qualities of agile processes to CMMI goals and practices.

Some mapping efforts have been done _(source)_:
  * Agile+ (AgileTek, basically extended XP to meet CMMI Level 3)
  * Microsoft Solutions Framework (Version 4 was agile “with some overhead” to achieve CMMI Level 3 consistency)
  * ASCEND (BAE Systems, claims CMMI Level 5 compatibility)

Possible areas of friction:

  1. Empowerment and trust versus micromanagement _(source)_
    * Process and Product Quality Assurance
    * Compliance (perhaps?) becomes the focus, not effective practices justified by results
  1. Elaboration and review of intermediate work products _(source)_
    * How much is enough to “define” and “elaborate”...
      1. requirements before?
      1. design and interfaces before?
      1. implementation and testing?
    * What is sufficient review?
    * Is bi-directional traceability necessary? To what level?
  1. Organization standards versus project standards _(source)_
    * Agile teams determine their own process and practices by consensus
    * Question: Does CMMI tailoring guidance allow project team data or consensus to overrule
      1. Organizational standards?
      1. Accumulated organizational performance data?
    * _Otherwise, process performers are no longer the process owners_
  1. If we modify CMMI to fit, is it still CMMI? If we modify our agile process, is it still agile?
    * "We're _basically_ CMMI lvl 4."
    * "Show me the certificate."
    * "..."

_[J. L. Dutton & R. S. McCabe: Agile/Lean Development and CMMI, SEPG 2006, 03.09.2006 [haettu: 01.04.2009](http://www.sei.cmu.edu/cmmi/adoption/pdf/dutton.pdf)]_
