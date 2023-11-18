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
import EquipeList from "views/ListEquipe.js";
import AddEquipe from "views/AddEquipe.js";
import AddDetailEquipe from "views/createDetailEquipe";
import DetailEquipeList from "views/ListDetailEquipe";
import EtudiantList from "views/Etudiant.js";
import DepList from "views/DepList.js";
import DepAdd from "views/DepAdd.js"
import AddContrat from "views/AddContrat";
import ContratList from "views/ContratList";
var routes = [
  //Users

  //universities
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

  //equipe
  {
    path: "/equipe",
    name: "Equipe List",
    icon: "nc-icon nc-shop",
    component: <EquipeList />,
    layout: "/admin",
  },
  {
    path: "/addequipe",
    name: "Add Equipe",
    icon: "nc-icon nc-shop",
    component: <AddEquipe />,
    layout: "/admin",
  },

  //detail equipe 
  {
    path: "/detailequipe",
    name: "DetailEquipe List",
    icon: "nc-icon nc-shop",
    component: <DetailEquipeList />,
    layout: "/admin",
  },
  {
    path: "/adddetailequipe",
    name: "Add DetailEquipe",
    icon: "nc-icon nc-shop",
    component: <AddDetailEquipe />,
    layout: "/admin",
  },

  //departement
   {
    path: "/deplist",
    name: "Departement List",
    icon: "nc-icon nc-shop",
    component: <DepList />,
    layout: "/admin",
  },
  {
    path: "/DepAdd",
    name: "Departement Add",
    icon: "nc-icon nc-shop",
    component: <DepAdd />,
    layout: "/admin",
  },

  //Contrat
  {
    path: "/contrat",
    name: "Contrat List",
    icon: "fas fa-book",
    component: <ContratList />,
    layout: "/admin",
  },
  {
    path: "/article/add",
    name: "Create Article",
    icon: "fas fa-pencil-alt",
    component: <AddContrat/>,
    layout: "/admin",
  },

  //Etudiant
  {
    path: "/etudiants",
    name: "Student Management",
    icon: "nc-icon nc-shop",
    component: <EtudiantList />,
    layout: "/admin",
  },
];
export default routes;
