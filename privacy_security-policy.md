Privacy policy
==============

Space Permission Handler for Confluence plugin was created to help administrators see and modify users' Confluence space permissions. To achieve this there has not been any need to collect extra usage information of the plugin. Only the very basic details about the plugin usage are transferred to Atlassian Marketplace. This data collection is automatically handled by the plugin framework and is controlled by Atlassian.

Security policy
===============

The plugin can only be installed to a Confluence server. There is no Cloud support at the moment. The plugin only stores the minimun mandatory information that is needed by any Confluence plugin. Users' space permissions are read and written to the Confluence's database.

Best efforts were taken to secure the REST API that the plugin provides and the user interface which both are restricted to Confluence administrators.  Source code has always been available as Open Source at Github. This enables users of the plugin to review and audit the plugin before using it.