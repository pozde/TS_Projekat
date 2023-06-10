import React from "react";

const NewsDefault = () => {
  return (
    <div>
      <h1>Novosti</h1>
      <p>U našem fitness centru uvijek se događa nešto novo i uzbudljivo. Ovdje 
        možete pronaći najnovije informacije o našim novostima, događajima i poboljšanjima.</p>
      <article>
        <h2>Nove sprave</h2>
        <p style={{margin: "20px 50px"}}> Za sve ljubitelje kardia predstavljamo novu <i>NordicTrack Commercial 1750 Treadmill</i> traku za trčanje! 
        Jedna od najboljih traka za trčanje sa brzom, nagibnom konzolom sa ekranom osjetljivim na dodir od 14 inča i tihim motorom na nagibu 
          koji se automatski prilagođava tokom vašeg vježbanja. I dalje sadrži sve pogodnosti koje možete 
          pronaći na NordicTrack mašinama – poput brzih tastera za skok do određene brzine ili nagiba jednim 
          dodirom umesto uzastopnog pritiskanja dugmeta.</p>
      </article>
      <article>
        <h2>Posebne ponude</h2>
        <p style={{margin: "20px 50px"}}>Uzbudljiva vijest za sve fitness entuzijaste! Upoznajte USN protein napitak, najnoviji dodatak našem 
          fitness centru koji će vam pomoći da ostvarite svoje fitness ciljeve na brži i efikasniji način.</p>

          <p style={{margin: "20px 50px"}}>USN Protein Napitak je visokokvalitetan izvor proteina koji je specijalno formuliran
           kako bi podržao vašu mišićnu regeneraciju, rast mišićne mase i oporavak nakon intenzivnih treninga. 
      Sadrži optimalnu kombinaciju proteina, vitamina i minerala koji su ključni za održavanje zdravog i snažnog tijela.
      Njegov sastav koji sadrži 25g proteina i 5g BCAA po dozi ga čini nevjerovatnim dodatkom vašem treningu!</p>
      </article>
      <article>
        <h2>Specijalni događaji</h2>
        <p style={{margin: "20px 50px"}}>Upoznajte naš novi HIIT program - Intenzivno vježbanje visokog intenziteta!</p>

        <p style={{margin: "20px 50px"}}>Vođen trenerom Benjaminom Pašićem, našim stručnjakom za funkcionalni trening, ovaj program je dizajniran 
          kako bi vam pružio ultimativno iskustvo sagorijevanja kalorija, izgradnje mišića i poboljšanja ukupne kondicije.</p>

        <p style={{margin: "20px 50px"}}>HIIT se temelji na konceptu intervalnog treninga u kojem se kombiniraju visoko intenzivni intervali 
          vježbanja s kratkim periodima odmora. Ovaj dinamičan i energičan program će vam pomoći da postignete 
          vrhunsku izdržljivost, poboljšate snagu, izgubite suvišne kilograme i oblikujete svoje tijelo.</p>

        <p style={{margin: "20px 50px"}}>Trener Benjamin Pašić će vas voditi kroz niz raznovrsnih vježbi koje uključuju kardio vježbe poput 
          skakanja užeta, sprintanja, burpeeja, ali i funkcionalne vježbe s opterećenjem kao što su čučnjevi, 
          izbačaji, trzaji i još mnogo toga.</p>

      </article>
      {/* Other news articles... */}
    </div>
  );
};

export default NewsDefault;
