"use client";
import React, { useState } from 'react';

function Input() {
    const [startWord, setStartWord] = useState('');
    const [goalWord, setGoalWord] = useState('');
    const [algorithm, setAlgorithm] = useState('ucs'); // default(?)

    const handleSubmit = (e) => {
        e.preventDefault();
        // Here you might send this data to your server or another function for processing
        console.log(`Start: ${startWord}, Goal: ${goalWord}, Algorithm: ${algorithm}`);
    };

    return (
        <div className="max-w-4xl mx-auto mt-10 space-y-4">
            <form onSubmit={handleSubmit} className="flex flex-col">
                <div className='mb-4'>
                    <label htmlFor="start" className="block text-sm font-medium text-gray-700">
                        Start
                    </label>
                    <input
                        type="text"
                        id="start"
                        value={startWord}
                        onChange={(e) => setStartWord(e.target.value)}
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm text-black"
                        placeholder="Enter start word"
                        required
                    />
                </div>
                <div className='mb-6'>
                    <label htmlFor="goal" className="block text-sm font-medium text-gray-700">
                        Goal
                    </label>
                    <input
                        type="text"
                        id="goal"
                        value={goalWord}
                        onChange={(e) => setGoalWord(e.target.value)}
                        className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm text-black"
                        placeholder="Enter goal word"
                        required
                    />
                </div>
                <fieldset className="mt-4">
                    <legend className="text-sm font-medium text-gray-700">Algorithm:</legend>
                    <div className="mt-2 space-y-2">
                        <div className="flex items-center">
                            <input
                                id="ucs"
                                name="algorithm"
                                type="radio"
                                value="ucs"
                                checked={algorithm === 'ucs'}
                                onChange={() => setAlgorithm('ucs')}
                                className="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300"
                            />
                            <label htmlFor="ucs" className="ml-3 block text-sm font-medium text-gray-700">
                                UCS
                            </label>
                        </div>
                        <div className="flex items-center">
                            <input
                                id="gbfs"
                                name="algorithm"
                                type="radio"
                                value="gbfs"
                                checked={algorithm === 'gbfs'}
                                onChange={() => setAlgorithm('gbfs')}
                                className="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300"
                            />
                            <label htmlFor="gbfs" className="ml-3 block text-sm font-medium text-gray-700">
                                Greedy BFS
                            </label>
                        </div>
                        <div className="flex items-center">
                            <input
                                id="astar"
                                name="algorithm"
                                type="radio"
                                value="astar"
                                checked={algorithm === 'astar'}
                                onChange={() => setAlgorithm('astar')}
                                className="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300"
                            />
                            <label htmlFor="astar" className="ml-3 block text-sm font-medium text-gray-700">
                                A*
                            </label>
                        </div>
                    </div>
                </fieldset>
                <button
                    type="submit"
                    className="mt-6 w-full flex justify-center py-2 px-4 border border-transparent rounded-lg shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                    Submit
                </button>
            </form>
        </div>
    );
}

export default Input;
