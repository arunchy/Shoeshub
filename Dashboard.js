import React, { useRef, useState } from 'react'
import styles from '../../styles/Update.module.css';
import Image from 'next/image';
import chart from '../../public/chart.png';
import money from '../../public/money.png';
import face from '../../public/face.jpg';
import shoes from '../../public/shoeslogo.png';
import line from '../../public/line.png';
import Link from 'next/link';
import { useRouter } from 'next/router';
function Dashboard(){
 const router=useRouter();
 console.log(router.pathname)
  return (

    <div className={`${styles.background} container-fluid`}>

      <div className='row'>
         <div className='col-sm-3 '>
            <h3 className='text-black-50'><b>ShoesHub DashBoard</b></h3>
         </div>
         <div className='col-sm-3 d-flex align-items-center'>
            <input className={`${styles.searchInput} form-control`} placeholder='Search' ></input>
         </div>
         <div className='col-sm-6 d-flex align-items-center justify-content-md-around justify-content-between'>

           <div> 
            <Link href='/Home' passHref legacyBehavior ><a className=''>Home</a></Link>
            <Link href='/admin/Crud' passHref legacyBehavior ><a className={ router.pathname==='/Crud'?styles.activelink:styles.link}>Crud</a></Link>
           </div>

            <div  className='border'>
              <select className='form-select'>
               <option>This Year</option>
               <option selected>This Month</option>
               <option>This Week</option>
               <option>3days ago</option>
              </select>
             
            </div>
            <div className={`${styles.profilediv}`} style={{height:'50px',width:'50px'}} >
               <Image src={face} height={50} width={50}></Image>
              </div>
         </div>
      </div>



<div className={`row d-flex justify-content-around my-4`}>
    <div className={`${styles.box} my-4 bg-white`} style={{height:'150px',width:'250px'}}>
     <div className='d-flex justify-content-between'>      <span className='bi bi-pie-chart text-primary fs-4'></span>
     <Image src={chart} height={60} width={60}></Image>
     </div>
     <div className='d-flex flex-row justify-content-between'> 
     <div><p className='text-black-50 '>Total Sales</p> </div>
     <div><p className='text-success '><span className='bi bi-arrow-bar-up'></span> 12.24%</p> </div>
     </div>
     <h4><b>10000</b></h4>
    </div>


    <div className={`${styles.box} my-4 bg-white`} style={{height:'150px',width:'250px'}}>
     <div className='d-flex justify-content-between'>      <span className='bi bi-eye text-dark fs-4'></span>
     <Image src={line} height={60} width={60}></Image>
     </div>
     <div className='d-flex flex-row justify-content-between'> 
     <div><p className='text-black-50 '>New Visitors</p> </div>
     <div><p className='text-success '><span className='bi bi-arrow-bar-up'></span> 12.24%</p> </div>
     </div>
     <h4><b>8858</b></h4>
    </div>


    <div className={`${styles.box} my-4 bg-white`} style={{height:'150px',width:'250px'}}>
     <div className='d-flex justify-content-between'>     
      <span className='bi bi-cash-coin text-success fs-4'></span>
     <Image src={money} height={60} width={60}></Image>
     </div>
     <div className='d-flex flex-row justify-content-between'> 
     <div><p className='text-black-50 '>Total Revenue</p> </div>
     <div><p className='text-success '><span className='bi bi-arrow-bar-up'></span> 12.24%</p> </div>
     </div>
     <h4><b>Rs 105000</b></h4>
    </div>


    <div className={`${styles.box} my-4 bg-white`} style={{height:'150px',width:'250px'}}>
     <div className='d-flex justify-content-between'><span className='bi bi-arrow-up-circle fs-4 text-info'></span>
     <Image src={shoes} height={60} width={60}></Image>
     </div>
     <div className='d-flex flex-row justify-content-around'> 
     <div><p className='text-black-50 '>Most Sale</p> </div>
     <div><p className='text-success '><span className='bi bi-arrow-bar-up'></span> 12.24%</p> </div>
     </div>
     <h4><b>Jordan jordan I </b></h4>
    </div>
    
   
</div>


<div className='row d-flex justify-content-around'>
 
    <div className='col-sm-6'>
        <p className='text-black-50'>Sales Table</p>
         <table className='table table-striped'>
            <thead>
                <tr>
                    <th scope='col'>BrandName</th>
                    <th scope='col'>Sales Rate</th>
                    <th scope='col'>Sales Revenue</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <td scope='row'>Adidas</td>
                <td>40%</td>
                <td> Rs 50000</td>
                </tr>

                <tr>
                <td scope='row'>Nike</td>
                <td>30%</td>
                <td>Rs 35000</td>
                </tr>

                <tr>
                <td scope='row'>Reebok</td>
                <td>20%</td>
                <td>Rs 15000</td>
                </tr>

                <tr>
                <td scope='row'>Adidas</td>
                <td>10%</td>
                <td>Rs 5000</td>
                </tr>
            </tbody>


         </table>
           
         
    </div>



    <div className='col-sm-4 bg-white'>
      
    <div className='row bg-white'>
    <p className='text-secondary'>Sales Rate</p>
           
<div className='border'>
    <p className='text-center'><b>Adidas</b></p> 
    <div>
    <div className="progress">
    <div className="progress-bar bg-info" role="progressbar" style={{ width: '40%' }} aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
    40%
    </div>    
   </div>
    </div>
  </div>

  <div className='border'>
    <p className='text-center'><b>Nike</b></p> 
    <div>
    <div className="progress">
    <div className="progress-bar bg-success" role="progressbar" style={{ width: '30%' }} aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
    30%
    </div>    
   </div>
    </div>
  </div>

  <div className='border'>
    <p className='text-center'><b>Reebok</b></p> 
    <div>
    <div className="progress">
    <div className="progress-bar bg-warning" role="progressbar" style={{ width: '20%' }} aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
    20%
    </div>    
   </div>
    </div>
  </div>

  <div className='border'>
    <p className='text-center'><b>Puma</b></p> 
    <div>
    <div className="progress">
    <div className="progress-bar bg-danger" role="progressbar" style={{ width: '10%' }} aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
    10%
    </div>    
   </div>
    </div>
  </div>


</div>



     <div className=''>
        <p className='text-black-50 my-2'>Sales Revenue</p>
     <div className="progress my-2" style={{height:'30px'}}>
      <div className="progress-bar bg-info" role="progressbar" style={{ width: '40%' }} aria-valuenow="15" aria-valuemin="0" aria-valuemax="100">Rs 50000</div>
      <div className="progress-bar bg-success" role="progressbar" style={{ width: '30%' }} aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">Rs 35000</div>
      <div className="progress-bar bg-warning" role="progressbar" style={{ width: '20%' }} aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">Rs 15000</div>
      <div className="progress-bar bg-danger" role="progressbar" style={{ width: '10%' }} aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">Rs 5000</div>
    </div>
     </div>
    </div>
</div>
</div>

)
}


export default Dashboard