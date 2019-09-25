<template>
  <div>
    <nav class="sidebar col-md-2 d-none d-md-block bg-light">
      <div class="sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <h5><router-link class="nav-link" to="/asset">Home</router-link></h5>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <h5><router-link class="nav-link" to="/asset/query">資產查詢</router-link></h5>
            </a>
          </li>
          <li class="nav-item">
            <h5><router-link class="nav-link" to="/asset/registered">資產入帳</router-link></h5>
            
          </li>
          <li class="nav-item"> 
            <a class="nav-link" href="#">
              <h5><router-link class="nav-link" to="/asset">資產異動</router-link></h5>
            </a>
          </li>
          <li class="nav-item">
            <h5><router-link class="nav-link" to="/asset">資產報廢</router-link></h5>
          </li>
          <li class="nav-item">
              <h5><router-link class="nav-link" to="/system/config">系統參數</router-link></h5>
          </li>
          
        </ul>

      </div>
    </nav>
  </div>
</template>



