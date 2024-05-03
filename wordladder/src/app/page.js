import Image from "next/image";
import Input from "./components/Input";

export default function Home() {
  return (
    <main className="bg-white flex min-h-screen flex-col justify-between p-24">
      <div>
        <Input />
      </div>
    </main>
  );
}
