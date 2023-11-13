/*!

=========================================================
* Paper Dashboard React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

* Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/

import UniversiteList from "views/UniversiteList.js";

// import AddUniversity from "views/AddUniversity.js";
import DepList from "views/DepList.js";

var routes = [
  //Users

  //Products
  {
    path: "/universites",
    name: "Universites List",
    icon: "nc-icon nc-shop",
    component: <UniversiteList />,
    layout: "/admin",
  },
  // {
  //   path: "/adduniversite",
  //   name: "Universites List",
  //   icon: "nc-icon nc-shop",
  //   component: <AddUniversity />,
  //   layout: "/admin",
  // },

  //dep
  {
    path: "/deplist",
    name: "Departement List",
    icon: "nc-icon nc-shop",
    component: <DepList />,
    layout: "/admin",
  },
];
export default routes;
